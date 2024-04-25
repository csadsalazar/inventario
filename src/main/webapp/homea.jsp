<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headera.jsp"%>
<%@include file="nava.jsp"%>

<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Home</h2>
        <section>
            <h3 id="general-percentage-header">Porcentaje general: <span id="general-percentage-value"></span>%</h3>
            <br>
            <canvas id="myChart" width="600" height="600"></canvas>
        </section>
    </div>
</main>

<script>
    fetch('PieChartServlet')
   .then(response => response.json())
   .then(data => {
        const labels = data.slice(0, -1).map(item => item.nombreDependencia);
        const porcentajes = data.slice(0, -1).map(item => item.porcentajeBienes);
        const generalPercentage = data[data.length - 1].porcentajeBienes; // Obtener el porcentaje general

        document.getElementById('general-percentage-value').innerHTML = generalPercentage.toFixed(2);

        const ctx = document.getElementById('myChart').getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    data: porcentajes,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)',
                        'rgba(54, 162, 235, 0.5)',
                        'rgba(255, 206, 86, 0.5)',
                        'rgba(75, 192, 192, 0.5)',
                        'rgba(153, 102, 255, 0.5)',
                        'rgba(255, 159, 64, 0.5)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'right', // Coloca la leyenda a la derecha
                        align: 'start', // Divide la leyenda en mitad izquierda y derecha
                        labels: {
                            font: {
                                size: 10 // Reducción del tamaño de la fuente de la leyenda
                            }
                        },
                        maxWidth: ctx.canvas.width / 2 // Establece el ancho máximo a la mitad del contenedor
                    }
                }
            }
        });
    });
</script>

<%@include file="footera.jsp"%>
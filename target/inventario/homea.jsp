<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headera.jsp"%>
<%@include file="nav.jsp"%>
    <div class="container">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">Inicio</li>
      </ol>
    </nav>
    </div>
    <main class="container">
            <div class="text-center">
                <h1>Inventario personalizado - INVIMA</h1>
                <h2>Inicio</h1>
            </div>
            <section>
                <h2 class="text-center" id="general-percentage-header">Porcentaje general: <span style="color: black;" id="general-percentage-value"></span>%</h2>
                <canvas id="myChart" width="400" height="50"></canvas>
            </section>
        </div>
    </main>
  
<script>
    fetch('GraphicOfObjects')
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
                        labels: {
                            font: {
                                size: 5 // Reducción del tamaño de la fuente de la leyenda
                            }
                        },
                        maxWidth: ctx.canvas.width / 3 // Establece el ancho máximo a la mitad del contenedor
                    }
                }
            }
        });
    });
</script>
<%@include file="footera.jsp"%>
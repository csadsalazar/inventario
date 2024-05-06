<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Cargue de inventario</h2>

    <style>
        /**********File Inputs**********/
        .container-input {
            text-align: center;
            background: #E0E0E0;
            border-top: 5px solid #139EC8;
            padding: 50px 0;
            border-radius: 6px;
            width: 50%;
            margin: 0 auto;
            margin-bottom: 20px;
        }
        
        .inputfile {
            width: 0.1px;
            height: 0.1px;
            opacity: 0;
            overflow: hidden;
            position: absolute;
            z-index: -1;
        }
        
        .inputfile + label {
            max-width: 80%;
            font-size: 1.25rem;
            font-weight: 700;
            text-overflow: ellipsis;
            white-space: nowrap;
            cursor: pointer;
            display: inline-block;
            overflow: hidden;
            padding: 0.625rem 1.25rem;
        }
        
        .inputfile + label svg {
            width: 1em;
            height: 1em;
            vertical-align: middle;
            fill: currentColor;
            margin-top: -0.25em;
            margin-right: 0.25em;
        }
        
        .iborrainputfile {
            font-size:16px; 
            font-weight:normal;
            font-family: 'Lato';
        }
        
        /* style 5 */
        
        .inputfile-5 + label {
            color: #139EC8;
        }
        
        .inputfile-5:focus + label,
        .inputfile-5.has-focus + label,
        .inputfile-5 + label:hover {
            color: #9f8465;
        }
        
        .inputfile-5 + label figure {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background-color: #139EC8;
            display: block;
            padding: 20px;
            margin: 0 auto 10px;
        }
        
        .inputfile-5:focus + label figure,
        .inputfile-5.has-focus + label figure,
        .inputfile-5 + label:hover figure {
            background-color: #139EC8;
        }
        
        .inputfile-5 + label svg {
            width: 100%;
            height: 100%;
            fill: #fff;
        }
    
        @media screen and (max-width: 50em) {
            .inputfile-7 + label strong {
                display: block;
            }
        }
        /**********End File Inputs**********/
    </style>
          
    <!--ESTILO 5-->
    <div class="container-input">
        <form action="UploadServlet" method="post" enctype="multipart/form-data">
            <input type="file" id="file" name="file">
            <label for="file">
                <figure>
                    <svg xmlns="http://www.w3.org/2000/svg" class="iborrainputfile" width="20" height="17" viewBox="0 0 20 17">
                        <path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path>
                    </svg>
                </figure>
                <span class="iborrainputfile">Seleccionar archivo</span>
            </label>
            <div class="button-container">
                <button class="button" type="submit">Cargar</button> <!-- Cambiado de <a> a <button> y agregado type="submit" -->
            </div>
        </form>
    </div>  
    </div>
</main>
<%@ include file="footera.jsp" %>

/*
 * FILE: script.js
 * AUTHOR: Francisco Mora
 */
$(function() {
	console.log('Ready....');

	$('#login').form({
		fields : {
			email : {
				identifier : 'user',
				rules : [ {
					type : 'empty',
					prompt : 'Usuario es obligatorio'
				} ]
			},
			password : {
				identifier : 'pass',
				rules : [ {
					type : 'empty',
					prompt : 'Password obligatoria'
				} ]
			}
		}
	});

	$('.ui.dropdown').dropdown();
	$('select.dropdown').dropdown();
	$('#listadoVentas,#listadoUsuarios,#listadoProductos, #listadoVentasActivas, #listaMisSubastas, #listaMisOfertas, #detalleDelaOferta').DataTable({
		buttons: [
			'pdfHtml5',
			'excel'
		],
		select: {info: false},
		language : {
			"sProcessing" : "Procesando...",
			"sLengthMenu" : "Mostrar _MENU_ registros",
			"sZeroRecords" : "No se encontraron resultados",
			"sEmptyTable" : "Ningún dato disponible en esta tabla =(",
			"sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
			"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sSearch" : "Buscar:",
			"sUrl" : "",
			"sInfoThousands" : ",",
			"sLoadingRecords" : "Cargando...",
			"oPaginate" : {
				"sFirst" : "Primero",
				"sLast" : "Último",
				"sNext" : "Siguiente",
				"sPrevious" : "Anterior"
			},
			"oAria" : {
				"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
				"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
			},
			"buttons" : {
				"copy" : "Copiar",
				"colvis" : "Visibilidad"
			}
		}
	});
	
	
	// esto para que funcione al agregar mas prodcutos para la venta de forma dinámica
	$('.detalle[detalle=0]').css('display', 'block');
	setTimeout(function(){
		// la wuea no se asigna altiro qp la mierad de backend no carga el contenido antes 
		$('.detalle[detalle=0] select').attr("required", "required");
		$('.detalle[detalle=0] input').attr("required", "required");
	}, 1000);
	
	$('#agregarProducto').click(function(){
		var i = parseInt($('#tablaProductos').attr("index"))+1;
		$('.detalle[detalle='+i+']').css('display', 'block');
		$('.detalle[detalle='+i+'] select').attr("required", "required");
		$('.detalle[detalle='+i+'] input').attr("required", "required");
		$('#tablaProductos').attr("index", i);
	});
	
	
	$('#tablaOfertar input').each(function(){
		var value = parseInt($(this).attr("min"));
		$(this).attr("min", value);
	});
	
	var d = $('.main').height();
	$('.main').append('<div class="footer" style="position: absolute; text-align:center; width: 80%; left: 10%">©Copyright 2019 Maipo Grande <br> <span>PORTAFOLIO TÍTULO - INGENIERÍA INFORMÁTICA</span><br><span>Gustavo Aguilar - Mayra Budini - Francisco Mora</span></div>')
	var h = $(window).height();
	var diff = d > h ? d + 10 : h + 10;
	$('.footer').css("top", diff);
	
	$('.ui.basic.modal')
	  .modal('show')
	;
	
	var valores = $('#valoresGrafico').text();
	console.log(valores);
	
	
});
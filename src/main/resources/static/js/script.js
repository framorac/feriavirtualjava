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
		"dom": "<'ui stackable grid'"+
				    "<'row'"+
				        "<'eight wide column'B>"+
				        "<'right aligned eight wide column'f>"+
				    ">"+
				    "<'row dt-table'"+
				        "<'sixteen wide column'tr>"+
				    ">"+
				    "<'row'"+
				        "<'seven wide column'i>"+
				        "<'right aligned nine wide column'p>"+
				    ">"+
				">",
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
	$('.main').append('<div class="footer" style="text-align:center;">©Copyright 2019 Maipo Grande <br> <span>PORTAFOLIO TÍTULO - INGENIERÍA INFORMÁTICA</span><br><span>Gustavo Aguilar - Mayra Budini - Francisco Mora</span></div>')
	var h = $(window).height();
	var diff = d > h ? d + 10 : h + 10;
	$('.footer').css("top", diff);
	
	$('.ui.basic.modal')
	  .modal('show')
	;
	
	setTimeout(function(){
		var x = [];
		var y = [];
		$('.valoresGrafico').each(function(){
			var valores = $(this).text();
			x.push(parseInt(valores.split("-")[0]));
			y.push(parseInt(valores.split("-")[1]));		
		});
		if(x.lenght > 0 && x.lenght > 0 ){
			
		Highcharts.chart('grafico', {
		    chart: {
		        type: 'line'
		    },
		    title: {
		        text: 'Cantidad de Productos x Oferta'
		    },
		    subtitle: {
		        text: 'MAIPO GRANDE'
		    },
		    xAxis: {
		        categories: x
		    },
		    yAxis: {
		        title: {
		            text: "Cantidad Productos"
		        }
		    },
		    plotOptions: {
		        line: {
		            dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
		        }
		    },
		    series: [{
		        name: 'Ofertas',
		        data: y
		    }],
		    credits:{
		    	text: "Maipo Grande"
		    }
		});
		}
		var k = [];
		var l = [];
		$('.valoresGrafico2').each(function(){
			console.log($(this));
			var valores = $(this).text();
			k.push(parseInt(valores.split("-")[0]));
			l.push(valores.split("-")[1]);		
		});
		
		if(k.length > 0 && l.length > 0 ){		
		// Build the chart
		Highcharts.chart('grafico2', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'pie'
		    },
		    title: {
		        text: 'Distribución de productos pedidos'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		    },
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: false
		            },
		            showInLegend: true
		        }
		    },
		    series: [{
		        name: 'Productos',
		        colorByPoint: true,
		        data: [{
		            name: l[0],
		            y: k[0],
		            sliced: true,
		            selected: true
		        }, {
		            name: l[1],
		            y: k[1]
		        },
		        {
		            name: l[2],
		            y: k[2]
		        }]
		    }],
		    credits:{
		    	text: "Maipo Grande"
		    }
		});
		}
		
		var m = [];
		var n = [];
		var o = [];
		$('.valoresGrafico3').each(function(){
			var valores = $(this).text();
			m.push(parseInt(valores.split("-")[0]));
			n.push(parseInt(valores.split("-")[1]));		
			o.push(parseInt(valores.split("-")[2]));
		});
		
		Highcharts.chart('grafico3', {
		    chart: {
		        zoomType: 'xy'
		    },
		    title: {
		        text: 'Capacidad Carga y Precio x Subasta'
		    },
		    subtitle: {
		        text: 'Maipo Grande'
		    },
		    xAxis: [{
		        categories: m,
		        crosshair: true
		    }],
		    yAxis: [{ // Primary yAxis
		        labels: {
		            format: '#{value}',
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        },
		        title: {
		            text: 'Capacidad Carga',
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        }
		    }, { // Secondary yAxis
		        title: {
		            text: 'Precio',
		            style: {
		                color: Highcharts.getOptions().colors[0]
		            }
		        },
		        labels: {
		            format: '${value}',
		            style: {
		                color: Highcharts.getOptions().colors[0]
		            }
		        },
		        opposite: true
		    }],
		    tooltip: {
		        shared: true
		    },
		    legend: {
		        layout: 'vertical',
		        align: 'left',
		        x: 120,
		        verticalAlign: 'top',
		        y: 100,
		        floating: true,
		        backgroundColor:
		            Highcharts.defaultOptions.legend.backgroundColor || // theme
		            'rgba(255,255,255,0.25)'
		    },
		    series: [{
		        name: 'Capacidad Carga',
		        type: 'column',
		        yAxis: 1,
		        data: n,
		        tooltip: {
		            valueSuffix: ' #'
		        }

		    }, {
		        name: 'Precio',
		        type: 'spline',
		        data: o,
		        tooltip: {
		            valueSuffix: '$'
		        }
		    }]
		});
		
	}, 1500);
	
});
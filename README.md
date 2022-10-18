# AirBooking

## Trabajo práctico N°2 de Desarrollo de Aplicaciones Móviles

#### Universidad:

Universidad Tecnológica Nacional - Facultad Regional Santa Fe

#### Autores: 

Chort Julio Alberto - Quarin Federico - Reynoso Valentín

#### Concepto:
  
Es una aplicación que permite buscar y reservar alojamientos turísticos temporales en diversas ciudades del mundo.
Se pueden encontrar departamentos ofrecidos por particulares u habitaciones disponibles en diversos hoteles. 

#### Requerimientos:

- Cuando el usuario inicia la aplicación le aparecerá una actividad con un formulario para realizar búsquedas sobre los alojamientos disponibles por diversos criterios.
- Luego de realizar la búsqueda se muestran los resultados en una lista con todos aquellos alojamientos que cumplen los criterios de búsqueda. El usuario tendrá la opción de:
  - Elegir uno de los alojamientos para ver en detalle
  - Agregarlo a su lista de favoritos.
  - Cambiar la visualización de los resultados a formato MAPA
  - Realizar una nueva búsqueda.
- Cuando el usuario está en la vista de detalle del alojamiento seleccionado, tendrá la opción de realizar una reserva sobre el mismo.
- Luego en la toolbar, tendrá un menú de opciones, que le permitirá elegir otras opciones de la aplicación:
  - Buscar: o lleva a la pantalla inicial de búsqueda
  - Mis Reservas: muestra la lista de reservas realizadas por el usuario
  - Mis Favoritos: muestra la lista de alojamientos marcados como favoritos
  - Configuración: muestra un panel de preferencias con información general de la aplicación.
  
## Tareas a realizar para la release 1:

- Personalizar la Toolbar agregando
  - Un título y colores personalizados.
  - Las opciones Buscar, Mis Reservas, Mis Favoritos, y Configuración.
  
- En el fragmento de búsqueda, agregar un formulario de búsqueda con diversa información de los alojamientos. El usuario deberá poder seleccionar:
  - Si quiere buscar en hoteles, departamentos o ambos.
  - La capacidad de personas
  - Si necesita Wifi (todos los hoteles tienen wifi en los departamentos es opcional)
  - Rango de precio mínimo y máximo.
  - Un spinner donde pueda elegir la Ciudad.
  - Dos botones, uno para resetear la búsqueda y otro para ejecutarla.
  
- Implemente que el botón buscar permita navegar a la pantalla de resultados, pero aun no realiza ninguna lógica de búsqueda.

- En el fragmento de resultados, implementar un recycler view que muestre información de la lista de alojamientos y además tiene un widget que permite agregarlo a favoritos. (Por el momento no debe filtrar, solo mostrar alojamientos en memoria)

- Cuando el usuario selecciona una fila del recycler, se navega a fragmento que muestra información de detalle. 

- La lista de búsqueda debe tener un botón que permita realizar una nueva búsqueda.

-  En el fragmento de detalle, debe mostrar la información del alojamiento seleccionado, y luego poder ingresar la fecha de inicio y fin de estadía y las cantidad de personas que se alojaran y se mostrará el precio. El usuario puede realizar la reserva o agregarlo a favoritos.

#### Resolución:

![menuBusqueda](https://user-images.githubusercontent.com/57647406/196050560-f70451c2-87ce-4418-b90d-921936393208.png)
![menuBusquedaSeleccion](https://user-images.githubusercontent.com/57647406/196050566-eaece0bd-2942-4dbb-941c-e3bbe7dee489.png)

![listaBusqueda](https://user-images.githubusercontent.com/57647406/196050577-9362b3af-cddd-413d-bf29-cce3b39c0559.png)
![listaBusquedaSeleccion](https://user-images.githubusercontent.com/57647406/196050579-54741539-3a46-4f0c-ab56-b5b2a8044483.png)

![detalleAlojamiento](https://user-images.githubusercontent.com/57647406/196050590-8962334d-cbd4-4f67-8d8a-7df712ded5a3.png)
![detalleAlojamientoSeleccion](https://user-images.githubusercontent.com/57647406/196050607-e734b4e1-d811-43a5-a001-9d169c948fce.png)

![detalleAlojamientoConfirmarReserva](https://user-images.githubusercontent.com/57647406/196050612-432aca24-dada-41bd-8c63-21b1eea59b41.png)
![menuBusquedaLuegoDeDetalleAlojamiento](https://user-images.githubusercontent.com/57647406/196050614-8c509818-5244-4946-a619-58d2d476571c.png)



#author: Alejandro Polo Carvajal
#language: es


Característica: Consumo con serenity-rest el verbos DELETE

  Yo como alejandro
  Quiero realizar consumos a un servicio de apirest de tipo delete
  Para comprobar la robustes de esta y validar si borra los datos

  Antecedentes:
    Dado "alejandro" obtiene la baseurl de la api

  @Successful
  Escenario: Borrar(delete) los datos que arroje el consumo de la api
    Cuando estructura la peticion a consumir
    Entonces el estado de la petición válido, confirma la eliminación exitosa del usuario
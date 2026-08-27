# language: es
Característica: Carrito de compras

  Antecedentes:
    Dado que el usuario ha iniciado sesión con un usuario válido

  Escenario: Consultar los productos agregados al carrito
    Cuando el usuario agrega el producto "Sauce Labs Backpack" al carrito
    Y el usuario agrega el producto "Sauce Labs Bike Light" al carrito
    Y el usuario abre el carrito
    Entonces el carrito contiene los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"

  Escenario: Eliminar un producto del carrito
    Cuando el usuario agrega el producto "Sauce Labs Backpack" al carrito
    Y el usuario agrega el producto "Sauce Labs Bike Light" al carrito
    Y el usuario abre el carrito
    Y el usuario elimina del carrito el producto "Sauce Labs Backpack"
    Entonces el carrito contiene únicamente el producto "Sauce Labs Bike Light"

  Escenario: Completar una compra
    Cuando el usuario agrega el producto "Sauce Labs Backpack" al carrito
    Y el usuario abre el carrito
    Y el usuario diligencia los datos de compra con nombre "Ana", apellido "Gómez" y código postal "110111"
    Entonces se muestra el mensaje de compra completada "Thank you for your order!"

  Escenario: Consultar un carrito vacío
    Cuando el usuario abre el carrito
    Entonces el carrito no contiene productos

  Escenario: Validar campos obligatorios de checkout
    Cuando el usuario agrega el producto "Sauce Labs Backpack" al carrito
    Y el usuario abre el carrito
    Y el usuario intenta continuar el checkout sin diligenciar datos
    Entonces se muestra el error de checkout "Error: First Name is required"

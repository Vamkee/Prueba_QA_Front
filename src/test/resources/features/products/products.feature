# language: es
Característica: Gestión de productos

  Antecedentes:
    Dado que el usuario ha iniciado sesión con un usuario válido

  Escenario: Visualizar el catálogo de productos
    Entonces se muestran 6 productos en el catálogo
    Y el catálogo contiene el producto "Sauce Labs Backpack"

  Escenario: Agregar un producto al carrito
    Cuando el usuario agrega el producto "Sauce Labs Backpack" al carrito
    Entonces el contador del carrito muestra "1"

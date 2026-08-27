# language: es
Característica: Filtrado de productos

  Antecedentes:
    Dado que el usuario ha iniciado sesión con un usuario válido

  Escenario: Ordenar productos por precio de menor a mayor
    Cuando el usuario ordena los productos por "Price (low to high)"
    Entonces el primer producto mostrado es "Sauce Labs Onesie"
    Y el precio del primer producto mostrado es "$7.99"

  Escenario: Ordenar productos por nombre de mayor a menor
    Cuando el usuario ordena los productos por "Name (Z to A)"
    Entonces el primer producto mostrado es "Test.allTheThings() T-Shirt (Red)"

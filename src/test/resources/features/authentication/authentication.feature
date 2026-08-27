# language: es
Característica: Autenticación

  Escenario: Inicio de sesión exitoso con un usuario válido
    Dado que el usuario está en la página de inicio de sesión de SauceDemo
    Cuando el usuario inicia sesión con usuario "standard_user" y contraseña "secret_sauce"
    Entonces se muestra la página de productos

  Escenario: El inicio de sesión falla para un usuario bloqueado
    Dado que el usuario está en la página de inicio de sesión de SauceDemo
    Cuando el usuario inicia sesión con usuario "locked_out_user" y contraseña "secret_sauce"
    Entonces se muestra el mensaje de error de inicio de sesión "Epic sadface: Sorry, this user has been locked out."

  Escenario: El inicio de sesión falla con credenciales inválidas
    Dado que el usuario está en la página de inicio de sesión de SauceDemo
    Cuando el usuario inicia sesión con usuario "invalid_user" y contraseña "invalid_password"
    Entonces se muestra el mensaje de error de inicio de sesión "Epic sadface: Username and password do not match any user in this service"

  Escenario: El inicio de sesión falla cuando el usuario está vacío
    Dado que el usuario está en la página de inicio de sesión de SauceDemo
    Cuando el usuario inicia sesión con usuario "" y contraseña "secret_sauce"
    Entonces se muestra el mensaje de error de inicio de sesión "Epic sadface: Username is required"

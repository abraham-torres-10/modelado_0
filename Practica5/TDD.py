"""Funcion para caluclar el costo de un envio de un paquete
Primer commit, va a ser la funcion sin ningun tipo de comportamiento"""

def calcular_envio(peso,tipo):
    costo = 50
    if peso > 5:
        costo = 50 + 10 * (peso - 5)
    if tipo == "express":
       costo = costo * 2 
    return costo
    raise NotImplementedError
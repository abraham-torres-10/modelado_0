"""Funcion para calcular el costo de un envio de un paquete
Primer commit, va a ser la funcion sin ningun tipo de comportamiento"""

#Ya quedo la refactorizacion, decidimos manejarlo asi para no repetir codigo
 y que no hubiera problemas con los tipos de datos
LIMITE_PESO = 5
COSTO_BASE = 50
COSTO_POR_KG_EXTRA = 10
TIPOS_VALIDOS = ("estandar", "express")

def calcular_envio(peso, tipo):
    if peso <= 0:
        raise ValueError("El peso debe ser mayor que cero")
    if tipo not in TIPOS_VALIDOS:
        raise ValueError("Tipo de envio invalido")

    costo = COSTO_BASE
    if peso > LIMITE_PESO:
        costo += COSTO_POR_KG_EXTRA * (peso - LIMITE_PESO)
    if tipo == "express":
        costo *= 2
    return costo
import pytest 

from TDD import calcular_envio
def test_envio_estandar():
    assert calcular_envio(3,"estandar")== 50

def test_envio_estandar_paquete_pesado_6kg():
    assert calcular_envio(6, "estandar") == 60

def test_envio_estandar_exacto():
    assert calcular_envio(5, "estandar") == 50

def test_envio_estandar_paquete_pesado_7kg():
    assert calcular_envio(7, "estandar") == 70

def test_envio_express_3kg():
    assert calcular_envio(3, "express") == 100

def test_envio_express_7kg():
    assert calcular_envio(7, "express") == 140

def test_envio_express_peso_cero():
    with pytest.raises(ValueError):
        calcular_envio(0, "express")

def test_envio_express_peso_negativo():
    with pytest.raises(ValueError):
        calcular_envio(-2, "express")

def test_tipo_invalido():
    with pytest.raises(ValueError):
        calcular_envio(3, "urgente")
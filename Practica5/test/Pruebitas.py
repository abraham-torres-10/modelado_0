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
from TDD import calcular_envio
def test_envio_estandar():
    assert calcular_envio(3,"estandar")== 50

def test_paquete_pesado():
    assert calcular_envio(6, "estandar") == 60
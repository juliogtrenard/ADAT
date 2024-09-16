"""
Escribe una función en el lenguaje de programación Python que reciba una oración,
elija dos palabras aleatorias de ella y forme una nueva palabra usando esas dos
palabras. Para crear la nueva palabra, los caracteres se seleccionarán
aleatoriamente de cada una de las dos palabras, uno por uno. Ejemplo: si palabra1
= 'casa' y palabra2 = 'voy', el primer carácter de la nueva palabra se elegirá
aleatoriamente entre los caracteres de 'c' y 'v'. Supongamos que se elige 'v',
entonces el siguiente carácter se elegirá aleatoriamente entre 'c' y 'o'. Repetiendo
este procedimiento con todos los caracteres, el resultado podría ser una palabra
como "vcaoysa"
"""

import random

def palabraNueva(oracion):
    lista = oracion.split()

    pal1 = random.choice(lista)
    pal2 = random.choice(lista)

    while pal1 == pal2:
        pal2 = random.choice(lista)
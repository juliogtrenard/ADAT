"""
Escribe una función en el lenguaje de programación Python llamada
rnd_word(fitxategi1, fitxategi2) que lea el primer archivo línea por línea, seleccione
una palabra aleatoria de cada línea y la escriba en el segundo archivo. Utiliza la
función choice del módulo random.
"""

import random

def rnd_word(fichero1, fichero2):
    file = open(fichero1, 'r')
    file2 = open(fichero2, 'w')
    linea = file.readline()

    while linea:
        palabras = []

        palabras = linea.split()

        file2.write(random.choice(palabras))
        file2.write('\n')

        linea = file.readline()

    file.close()
    file2.close()

rnd_word('Ejercicios_Python_Ficheros/frases.txt', 'Ejercicios_Python_Ficheros/palabras.txt')
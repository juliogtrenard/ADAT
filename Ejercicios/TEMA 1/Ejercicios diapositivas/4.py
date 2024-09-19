#Escribe un programa que añada espacios antes de la palabra, el número
#de espacios deberá ser el doble del tamaño de la palabra.

palabra = input("Escribe una palabra: ")
ESPACIO = 0x20
aux = chr(ESPACIO)
espacios = aux * len(palabra) * 2
espacios = espacios + palabra
print(espacios)
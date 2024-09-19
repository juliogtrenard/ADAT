#Escribe un programa que busque el índice de una letra en una palabra y si
#no contiene la letra devuelva -1. (sin utilizar la función find y utilizando la
#función find)

def indice(palabra, letra):
    for i in range(len(palabra)):
        if palabra[i] == letra:
            return i
    return -1

#Sin find
palabra = input("Escribe una palabra: ")  
letra = input("Escribe una letra: ")  
print("El indice de la letra", letra, "es:", indice(palabra, letra))

#Con find
palabra = input("Escribe una palabra: ")  
letra = input("Escribe una letra: ")
print("El indice de la letra", letra, "es:", palabra.find(letra))
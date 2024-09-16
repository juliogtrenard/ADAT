#Modifica el programa anterior de forma que cada una de las funcionalidades del programa se
#ejecute mediante una función.

def calcularSumatorio(lista):
    return sum(lista)

def calcularMedia(sumatorio, cantNumeros):
    media = sumatorio / cantNumeros
    return media

def calcularMinimo():
    return min(lista)

def calcularMaximo():
    return max(lista)

print("Introduce 10 números impares: ")

lista = []
for i in range(10):
    num = int(input())
    while num % 2 == 0:
        print("Introduce un número impar: ")
        num = int(input())
    
    lista.insert(i, num)

print("¿Qué desea hacer con la lista?", '\n', "1. Sumatorio", '\n', "2. Media", '\n', "3. Máximo",
      '\n', "4. Mínimo", '\n', "", '\n', "0. Salir")

opcion = int(input())

while opcion < 0 or opcion > 4:
    print("Selecciona una opción correcta (0-4)")
    opcion = int(input())

if opcion == 1:
    print(calcularSumatorio(lista))
elif opcion == 2:
    print(calcularMedia(calcularSumatorio(lista), len(lista)))
elif opcion == 3:
    print(calcularMaximo())
elif opcion == 4:
    print(calcularMinimo())
elif opcion == 0:
    print("Programa finalizado.")
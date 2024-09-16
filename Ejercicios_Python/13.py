"""Adapta y amplia el programa anterior para que una vez introducidos los 10 números
impares, se muestre un menú en pantalla con 5 opciones:
¿Que desea hacer con la lista?
1. Sumatorio
2. Media
3. Máximo
4. Mínimo
0. Salir

Y después muestre el resultado de la opción seleccionada.
"""

print("Introduce 10 números impares: ")

lista = []
sumatorio = 0
for i in range(10):
    num = int(input())
    while num % 2 == 0:
        print("Introduce un número impar: ")
        num = int(input())
    
    lista.insert(i, num)
    sumatorio += num

media = sumatorio / len(lista)
minimo = min(lista)
maximo = max(lista)

print("¿Qué desea hacer con la lista?", '\n', "1. Sumatorio", '\n', "2. Media", '\n', "3. Máximo",
      '\n', "4. Mínimo", '\n', "", '\n', "0. Salir")

opcion = int(input())

while opcion < 0 or opcion > 4:
    print("Selecciona una opción correcta (0-4)")
    opcion = int(input())

if opcion == 1:
    print(sumatorio)
elif opcion == 2:
    print(media)
elif opcion == 3:
    print(maximo)
elif opcion == 4:
    print(minimo)
elif opcion == 0:
    print("Programa finalizado.")
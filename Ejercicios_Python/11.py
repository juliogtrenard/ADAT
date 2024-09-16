#Modifica el programa anterior, de manera que al terminar de guardar los números en la lista
#se impriman la lista, el sumatorio y la media de todos los número de dicha lista.

print("Introduce 10 números: ")

lista = []
sumatorio = 0
for i in range(10):
    num = int(input())
    lista.insert(i, num)
    sumatorio += num

print(lista, '\n', "Sumatorio: ", sumatorio, '\n', "Media: ", sumatorio / len(lista))
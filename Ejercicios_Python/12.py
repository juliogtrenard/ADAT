#Modifica el programa anterior de forma que la lista de 10 números sean números impares.
#(hay que asegurarse de que lo que se introduce en la lista son números).

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

print(lista, '\n', "Sumatorio: ", sumatorio, '\n', "Media: ", sumatorio / len(lista))
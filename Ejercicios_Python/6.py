#Escribe un programa que pida por pantalla un texto y calcule el porcentaje
#de palabras que no tienen a.

frase = input("Escribe una frase: ")

cont = 0
lista = frase.split(' ')

for palabra in lista:
    if 'a' in palabra:
        cont = cont + 1

porc = cont * 100 / len(lista)

print("Palabras con a:", cont)
print("El porcentaje de palabras con a es:", porc)
#Escribe un programa que imprima solo
#las palabras que tienen más de 10 letras

def contarLetras(palabra):
    cont = 0
    for i in range(len(palabra)):
        cont = cont + 1
    
    return cont

for i in range(10):
    pal = input()

    if(contarLetras(pal) > 10):
        print(pal)
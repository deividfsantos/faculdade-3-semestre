import sys
import matplotlib.pyplot as plt
from matplotlib import collections as mc

if len(sys.argv) == 1:
    print("plotgraph.py [arquivo de saida com o grafo]")
    sys.exit(1)

# Leitura do arquivo de pontos

arq = open("dados.csv")

x = []
y = []

for linha in arq:
    data = linha.split(';')
    x.append(float(data[1]))
    y.append(float(data[0]))

arq.close()

# Leitura do arquivo de saida

edges = []
colours = []

arq2 = open(sys.argv[1])

for linha in arq2:
    data = linha.split()
    print(data[0],data[1])
    i0 = int(data[0])
    i1 = int(data[1])
    pt1 = (x[i0], y[i0])
    pt2 = (x[i1], y[i1])
    # Arestas marcadas com 0 sao cinza claro
    if int(data[2]) == 0:
        colours.append([0,0,0,0.5])
    else:
        # Arestas marcadas com 1 sao vermelhas
        colours.append([1,0,0,1])
    edges.append([pt1,pt2])

arq2.close()

edgecol= mc.LineCollection(edges, colors=colours, linewidths=1)

plt.rcParams["figure.figsize"] = (6,6)
fig, ax = plt.subplots()
ax.add_collection(edgecol)
ax.autoscale()
ax.margins(0.1)
plt.axis('equal')
plt.scatter(x,y,s=1)
plt.show()


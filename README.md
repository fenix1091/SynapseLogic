# SynapseLogic - Tarjeta CRC

# Clase - Responsabilidades | Colaboradores 

## Neurona
1. Obtener las propiedades biológicas de un nodo de la red (ID y tipo: Sensorial, Motor o Interneurona). | 
2. Mantener la referencia a las conexiones que nacen de ella misma. | Sinapsis 
3. Funcionar como un nodo para el recorrido de la red neuronal. | Neurona 

## HashTable 
1. Obtener los datos del diccionario global de neurotransmisores. | Neurotransmisor
2. Calcular el índice de mapeo mediante una función hash propia. 
3. Resolver las colisiones internas asegurando los tiempos de busqueda costante O(1). | Neurotransmisor 
4. Insertar, vaciar o extraer las propiedades bioquímicas usando el identificador molecular. | Neurotransmisor 

## Sinapsis 
1. Conocer la neurona destino. | Neurona
2. Almacenar la información del neurotransmisor asociado.
3. Mantener una distancia específica y el coeficiente de eficiencia (k).
4. Dar paso al cálculo del peso efectivo (La fórmula W). | Tabla Hash | Neurotransmisor 
5. Mostrar el factor de deterioro multiplicando k por 1.2.

## Neurotransmisor 
1. Almacenar los detalles moleculares de un neurotransmisor.
2. Dar los atributos para consultar un neurotransmisor: id, nombre, efecto biológico (excitatorio/inhibitorio), velocdad y descripción. 

## Grafo 
1. Almacenar y darle forma a la red neuronal mediante una lista de adyacencia. | Neurona | Sinapsis
2. Registar y conectar nuevas neuronas y sinapsis dentro de la estructura de punteros. | Neurona | Sinapsis
3. Emplear recorrido BFS desde la neurona principal para encontrar zonas aisladas. | Neurona
4. Usar el algoritmo Dijkstra para la ruta de activación eléctrica más conveniente. | Neurona | Sinapsis
4. Proveer acceso al diccionario de neurotransmisores. | HashTable
   


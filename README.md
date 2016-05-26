# SISE15Puzzle


Parametry wiersza poleceń:


- -b/--bfs porządek	                Strategia przeszukiwania wszerz
- -d/--dfs porządek	                Strategia przeszukiwania w głąb
- -i/--idfs porządek	                Strategia przeszukiwania w głąb z iteracyjnym pogłębianiem
- -a/--a id_strategii id_heurystyki	Strategia najpierw najlepszy

gdzie:
- id_strategii:
    - a       strategia A*
    - ida     strategia A* z iteracyjnym poglebianiem
    - bf      strategia najpierw najlepszy
    - cbf     wariacja na temat strategii najpierw najlepszy, nie tworzy grafu, zapobieganie zapetleniom na poziomie planszy

- id_heurystyki:
    - 1   liczba pol poza swoim polozeniem
    - 2   suma dystansow miedzy polem i jego poprawnym polozeniem (Manhattan distance)

	
- porzadek 
jest to dowolna permutacja zbioru {L, P, G, D}, jesli natomiast bedzie zawieral R lub r, to porzadek bedzie losowany kazdorazowo

# SISE N-Puzzle


Parametry wiersza poleceń:
<br />
<br />
-b/--bfs porządek	                Strategia przeszukiwania wszerz<br />
-d/--dfs porządek	                Strategia przeszukiwania w głąb<br />
-i/--idfs porządek	                Strategia przeszukiwania w głąb z iteracyjnym pogłębianiem<br />
-a/--a id_strategii id_heurystyki	Strategia najpierw najlepszy<br />
<br />
gdzie:<br />
id_strategii:<br />
&nbsp;&nbsp;    a &nbsp;&nbsp;&nbsp;&nbsp;      strategia A*<br />
&nbsp;&nbsp;    ida &nbsp;    strategia A* z iteracyjnym poglebianiem<br />
&nbsp;&nbsp;    bf  &nbsp;&nbsp;&nbsp;    strategia najpierw najlepszy<br />
&nbsp;&nbsp;    cbf &nbsp;    wariacja na temat strategii najpierw najlepszy, nie tworzy grafu, zapobieganie zapetleniom na poziomie planszy<br />
<br />
id_heurystyki:<br />
&nbsp;&nbsp;    1&nbsp;&nbsp;   liczba pol poza swoim polozeniem<br />
&nbsp;&nbsp;    2&nbsp;&nbsp;   suma dystansow miedzy polem i jego poprawnym polozeniem (Manhattan distance)<br />
<br />
Porzadek jest to dowolna permutacja zbioru {L, P, G, D}, jesli natomiast bedzie zawieral R lub r, to porzadek bedzie losowany kazdorazowo<br />
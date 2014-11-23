# Zon

A scala tool for interacting with AmaZON's AWS

## Timings

https://docs.google.com/spreadsheets/d/1lYCztN0CRkXrBScDJmAFmf9EEBEqMLN3VN2fdvFiAvs/edit?usp=sharing

Averages (time(1)'s real timing over 10 consequtive runs):
* Java: 9.2s
* Nailgun: 9.1s
* Python: 11.2s

Adding parallelism:
* Java: 4.6s
* Nailgun: 3.6s
* Python: 8.9s

| java seq | ng seq | py seq  | java par | ng par | py par |
|----------|--------|---------|----------|--------|--------|
| 10.831   |  8.892 | 10.801  | 4.558    | 6.800  | 7.533  |
|  8.168   |  9.989 | 10.044  | 4.291    | 3.084  | 7.662  |
|  9.066   |  7.695 | 12.957  | 4.277    | 2.990  | 21.467 |
|  9.757   | 13.484 | 10.612  | 4.435    | 3.148  | 7.052  |
|  8.858   | 10.983 | 11.270  | 4.337    | 3.077  | 7.762  |
|  8.077   |  8.369 | 11.036  | 4.506    | 4.521  | 7.345  |
|  8.639   |  7.407 | 11.504  | 6.796    | 2.834  | 10.432 |
|  9.336   |  7.895 | 13.870  | 4.187    | 2.962  | 5.337  |
|  8.576   |  7.562 |  8.625  | 4.185    | 3.219  | 5.234  |
| 11.027   |  8.732 | 10.859  | 4.199    | 3.126  | 9.271  |
|          |        |         |          |        |        |
| 9.2335   | 9.1008 | 11.1578 | 4.5771   | 3.5761 | 8.9095 |

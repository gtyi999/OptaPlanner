************************************************************************
file with basedata            : md359_.bas
initial value random generator: 1406400898
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  166
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       22       13       22
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           6   9  14
   3        3          2           5   8
   4        3          2           7  11
   5        3          3          12  17  19
   6        3          2          18  19
   7        3          2          14  16
   8        3          2           9  10
   9        3          2          15  16
  10        3          3          12  13  16
  11        3          2          15  17
  12        3          2          14  18
  13        3          1          17
  14        3          2          15  20
  15        3          1          21
  16        3          2          19  20
  17        3          2          18  21
  18        3          1          20
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     2       5   10    8    3
         2     3       5    9    6    2
         3     5       3    6    5    1
  3      1     2       8    7    8    9
         2     3       6    4    7    9
         3    10       5    2    7    9
  4      1     1      10    3    4    3
         2     5       9    2    4    3
         3     6       9    1    4    2
  5      1     4       4    4    7    6
         2     4       5    4    5    5
         3     9       2    4    3    3
  6      1     7       3    5    5    8
         2     8       3    4    5    7
         3    10       2    3    5    4
  7      1     1       2    7    5    6
         2     7       2    4    4    5
         3     8       2    3    3    5
  8      1     5       8    6    4    6
         2     8       6    4    4    6
         3    10       3    2    1    6
  9      1     4       4    9    4    5
         2     7       3    9    4    4
         3    10       2    9    4    4
 10      1     6       8   10   10    6
         2     6       9    9    8    7
         3     9       5    9    5    3
 11      1     1       5    8    3    3
         2     6       5    6    2    2
         3     9       5    4    2    2
 12      1     1       9    7    8    6
         2     2       7    6    7    4
         3     9       5    5    5    2
 13      1     1       7    9   10    6
         2     1       8    9    7    9
         3     4       4    8    6    6
 14      1     2       7    6   10    8
         2     4       6    5    8    7
         3     6       6    4    8    6
 15      1     3       4    8    9    8
         2     5       2    3    8    8
         3     7       1    2    8    6
 16      1     4       9    6    8    7
         2     4      10    5    8    8
         3    10       8    1    8    6
 17      1     3       7    7    8    9
         2     7       6    7    8    4
         3     9       2    4    8    3
 18      1     1       7    3    9    8
         2     8       5    3    9    5
         3    10       3    2    8    3
 19      1     1       9    6    4    6
         2     3       7    5    4    4
         3     7       7    3    3    4
 20      1     2       5    7    1    9
         2     5       5    5    1    9
         3     9       2    1    1    8
 21      1     3       9    8    7   10
         2     8       8    8    5   10
         3     9       7    7    5    9
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   23   26  107  103
************************************************************************

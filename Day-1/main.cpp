#include <iostream>
#include <fstream>
#include <string>

using namespace std;

const char* path1 = "measures.txt";
const char* path2 = "sums.txt";

int contLines(const char* path){
  ifstream file;
  int i, dato, cont = 0;
  file.open(path);
  if(!file)
    cout << "File error" << endl;
  else{
    while(file >> dato){
      cont++;
    }
    file.close();
  }
  return cont;
}

void setArray(int A[], const char* path){
  ifstream file;
  int i=0, dato, cont = 0;
  file.open(path);
  if(!file)
    cout << "File error" << endl;
  else{
    while(file >> dato){
      A[i] = dato;
      i++;
    }
    file.close();
  }
}

void contLargerMeasures(int measures[], int n){
  int cont = 0;
  for(int i = 0; i<n-1; i++){
    if(measures[i+1]>measures[i]){
      cont++;
    }
  }
  cout << "Counting: " << cont << endl;
}

void contLargerSums(int measures[], int n){
  int sum1 =0, sum2 =0, cont =0, j=1;
  sum1 = measures[0] + measures[1] + measures[2];
  for(int i = 1; i<n-2; i++){
    sum2 = measures[i] + measures[i+1] + measures[i+2];
    if(sum2 > sum1){
      cont++;
    }
    sum1 = sum2;
  }
  cout << "Counting: " << cont << endl;
}

int main() {
  int n = contLines(path1);
  int measures[n];
  setArray(measures, path1);
  cout << "Part 1" << endl;
  contLargerMeasures(measures, n);

  cout << "Part 2" << endl;
  contLargerSums(measures, n);
} 

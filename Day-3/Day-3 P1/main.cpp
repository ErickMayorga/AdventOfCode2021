#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
using namespace std;

const char* path1 = "binary.txt";
const int NBIN = 12;

int contLines(const char* path){
  ifstream file;
  string data;
  int cont = 0;
  file.open(path);
  if(!file)
    cout << "File error" << endl;
  else{
    while(file >> data){
      cont++;
    }
    file.close();
  }
  return cont;
}

void setMatrix(int A[][NBIN], const char* path){
  ifstream file;
  int i=0;
  string dato;
  
  file.open(path);
  if(!file)
    cout << "File error" << endl;
  else{
    while(file >> dato){
      for(int k=0; k<NBIN; k++){
        A[i][k] = dato[k] - '0';
      }
      i++;
    }
    file.close();
  }
}

int commonBit(int A[][NBIN], int n, int bit){
  
  int cont1 = 0, cont0 = 0, result = 0;

  for(int i=0; i<n; i++){
    if(A[i][bit]==1)
      cont1++;
    else
      cont0++;
  }

  if(cont1>cont0)
    result=1;
  return result;
}

int binToDec(long bin){
  int e = 0, x = 0, dec = 0;
  
  while(bin/10 !=0){
    x = bin%10;
    dec += x * pow(2, e);
    e++;
    bin /= 10;
   }

   dec += bin * pow(2,e);
   return dec;
}

int main() {
  int n = contLines(path1);
  int bin[n][NBIN];
  setMatrix(bin, path1);

  //Part 1

  string gamma = "", epsilon= "";
  int bit = 0;
  for(int i=0; i<NBIN; i++){
    bit = commonBit(bin,n, i);
    gamma.push_back(bit + '0');
    epsilon.push_back(!bit + '0');
  }

  int gammaDec = binToDec(stol(gamma));
  int epsilonDec = binToDec(stol(epsilon));
  cout << "PART I" << endl;
  cout << "Gamma: " << gammaDec << endl;
  cout << "Epsilon: " << epsilonDec << endl;
  cout << "Multiplication: " << gammaDec * epsilonDec << endl;
} 
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

const char* path1 = "planned_course.txt";

int hposition = 0, depth = 0, aim =0;

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

void setArray(string A[], int B[], const char* path){
  ifstream file;
  int i=0, cont1 =0, cont2 =0;
  string data;
  file.open(path);

  if(!file)
    cout << "File error" << endl;
  else{
    while(file >> data){
      if(i%2 == 0){
        A[cont1] = data;
        cont1++;
      }else{
        B[cont2] = stoi(data);
        cont2++;
      }
      i++;
    }
    file.close();
  }
}

void execInstruction(string command, int value){
  if(command == "up"){
    depth -= value;
  }else if(command == "down"){
    depth += value;
  }else{
    hposition += value;
  }
}

void execInstruction2(string command, int value){
  if(command == "up"){
    aim -= value;
  }else if(command == "down"){
    aim += value;
  }else{
    hposition += value;
    depth += aim*value;
  }
}

int main() {
  int n = contLines(path1)/2;
  string comands[n];
  int values[n];

  setArray(comands, values, path1);

  cout << "Part I" << endl;
  for(int i=0; i<n; i++){
    execInstruction(comands[i], values[i]);
  }
  cout << "Horizontal Position: " << hposition << endl;
  cout << "Depth: " << depth << endl;
  cout << "Multiplication: " << hposition*depth << endl;

  hposition = 0;
  depth=0;
  aim =0;

  cout << endl << "Part II" << endl;
  for(int i=0; i<n; i++){
    execInstruction2(comands[i], values[i]);
  }
  cout << "Horizontal Position: " << hposition << endl;
  cout << "Depth: " << depth << endl;
  cout << "Aim: " << aim << endl;
  cout << "Multiplication: " << hposition*depth << endl;
} 
/*
Using the Collatz Conjecture this program algorithmically
determines the number of steps nessicary for n = 1. Written
by Caelan Hadley.

© 2021 Caelan Hadley.
*/
#include <iostream>

int main() {
    int n = 1000001;
    int step = 0;

    std::cout << "Starting with >> n = " << n << "\t step = " 
        << step << "\n";

    while(n != 1 && step < 10000) {
        // If n is even:
        if (n % 2 == 0){
            n = n / 2;
        }
        // If n is odd:
        else {
            n = (n * 3) + 1;
        }
        std::cout << "n = " << n << "\t step: " << step << "\n";
        step++;
    }
}
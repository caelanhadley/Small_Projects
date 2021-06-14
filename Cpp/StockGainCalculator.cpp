/*
This is a personal program that I wrote to
calculate the gains I have made trading any
stocks I have invested in. Written and developed
by Caelan Hadley.

© 2021 Caelan Hadley. All Rights Reserved.
*/
#   include <iostream>

int main() {
	std::string stockname = "DOGE";
	double buyin = 0;
	double amtin = 0;
	double sellout = 0;

	double total_invest_in = 0;
	double total_gains = 0;

	bool exit = false;

	std::cout << "Enter cost per stock: ";
	std::cin >> buyin;
	std::cout << "\nEnter # of stock bought: ";
	std::cin >> amtin;
	std::cout << "\n\n";

	while (!exit) {

		std::cout << "Enter Bid out: ";
		std::cin >> sellout;

		total_invest_in = amtin * buyin;
		total_gains = (sellout * amtin) - total_invest_in;

		std::cout << "{" << stockname << "}\n"
			<< "In - \t[" << buyin << "] x " << amtin << " = " << total_invest_in << "\n"
			<< "Out - \t[" << sellout << "] x " << amtin << " = " << (total_gains + total_invest_in) << "\n\n"
			<< "Gain >> " << total_gains << " <<\n\n"
			<< "\tFrom: " << total_invest_in << "\n"
			<< "\tTo  : " << (total_gains + total_invest_in) << "\n";

		std::cout << "\n1 - Exit: ";
		std::cin >> exit;
		std::cout << "\n\n\n\n";
		system("cls");
	}
	

}
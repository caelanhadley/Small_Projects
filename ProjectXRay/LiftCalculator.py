from typing import Collection
import matplotlib.pyplot as plt
from numpy import math

class Lift():
    def __init__(self, velocity, density, wing_area, alpha):
        self.velocity = velocity
        self.density = density # Air rho = 1.225
        self.wing_area = wing_area
        self.alpha = alpha
        self.dynamic_pressure = 0
        
        self.af_data_alpha = [
            -8.5,-8.25,-8,-7.75,-7.5,-7.25,-7,-6.75,-6.5,-6.25,-6,-5.75,-5.5,-5.25,-5,-4.75,-4.5,-4.25,-4,-3.75,-3.5,-3.25,-3,-2.75,-2.5,-2.25,-2,-1.75,-1.5,-1.25,-1,-0.75,-0.5,-0.25,0,0.25,0.5,0.75,1,1.25,1.5,1.75,2,2.25,2.5,2.75,3,3.25,3.5,3.75,4,4.25,4.5,4.75,5,5.25,5.5,5.75,6,6.25,6.5,6.75,7,7.25,7.5,7.75,8,8.25,8.5,8.75,9,9.25,9.5,9.75,10,10.25,10.5,10.75,11,11.25,11.5,11.75,12,12.25,12.5,12.75,13,13.25,13.5,13.75,14.5,14.75,15
        ]
        self.af_data_cl = [
            -0.341,-0.3291,-0.3587,-0.3342,-0.3487,-0.3539,-0.3507,-0.3882,-0.3717,-0.407,-0.4206,-0.4114,-0.4177,-0.4281,-0.4512,-0.4331,-0.4314,-0.4061,-0.3795,-0.3552,-0.3276,-0.3046,-0.2805,-0.2551,-0.2291,-0.2061,-0.1821,-0.1587,-0.1351,-0.1094,-0.0812,-0.0716,-0.0468,-0.0042,0.0506,0.1051,0.157,0.1995,0.2387,0.2766,0.313,0.3483,0.3833,0.4186,0.4544,0.4906,0.5275,0.5643,0.6003,0.6355,0.6685,0.6981,0.7256,0.7506,0.7734,0.7952,0.8193,0.8676,0.88,0.8907,0.9033,0.9267,0.9915,1.0017,1.0164,1.0478,1.1029,1.1433,1.1591,1.1777,1.2049,1.2442,1.2528,1.2746,1.3042,1.3144,1.328,1.3409,1.3177,1.337,1.2972,1.2377,1.112,1.3618,1.3213,1.3277,1.3716,1.3285,1.292,1.2584,1.2881,1.2556,0.8641
        ]

    def calculate_dynamic_pressure(self, velocity, density):
        return (0.5 * density * pow(velocity, 2))

    def generate_dynamic_pressure(self):
        self.dynamic_pressure = (0.5 * self.density * pow(self.velocity, 2))

    def set_dynamic_pressure(self, dynamic_pressureIn):
        self.dynamic_pressure = dynamic_pressureIn
    
    def get_dynamic_pressure(self):
        return self.dynamic_pressure
    
    def set_velocity(self, velocity_in):
        self.velocity = velocity_in
    
    def get_velocity(self):
        return self.velocity
    
    def set_density(self, density_in):
        self.density = density_in
    
    def get_density(self):
        return self.density
    
    def set_wing_area(self, wing_area_in):
        self.wing_area = wing_area_in
    
    def get_wing_area(self):
        return self.wing_area
    
    def set_alpha(self, alpha_in):
        self.alpha = alpha_in
    
    def get_alpha(self):
        return self.alpha

    def find_cl_from_alpha(self):
        iter = 0
        closest_value = self.af_data_alpha[0]
        position = -1
        for i in self.af_data_alpha:
            if (self.alpha - self.af_data_alpha[iter]) >= 0:
                closest_value = self.af_data_alpha[iter]
                position = iter 
            iter += 1
        return self.af_data_cl[position]

    def calculate_lift(self):
        return (self.dynamic_pressure * self.find_cl_from_alpha() * self.wing_area)
    
    def toString(self):
        print(str(self.get_velocity()), " speed (feet per second)")
        print(str(self.get_alpha()), " alpha (degrees)")
        print(str(self.get_wing_area()), " wing area (ft^2)")
        print(str(self.get_dynamic_pressure()), " dynamic pressure (unit)")
        print(str(self.calculate_lift()), " Lift (lbs)")

class Plotter():
    def __init__(self):
        self.x_data = []
        self.y_data = []

        self.x_sub_data = []
        self.y_sub_data = []
    
    def getPlotData(self, liftObj, number_of_points):
        for i in range(0, number_of_points):
            #liftObj.set_wing_area(i)
            alpha_temp = 15 * (i / number_of_points)
            liftObj.set_alpha(alpha_temp)
            liftObj.generate_dynamic_pressure()
            self.add_plot_data(i, liftObj.calculate_lift())
            self.add_sub_plot_data(i, alpha_temp)
    
    def add_plot_data(self, x_data_in, y_data_in):
        self.x_data.append(x_data_in)
        self.y_data.append(y_data_in)
    
    def add_sub_plot_data(self, x_data_in, y_data_in):
        self.x_sub_data.append(x_data_in)
        self.y_sub_data.append(y_data_in)

    
    def plot_data(self):
        plt.figure(1)
        plt.subplot(211)
        plt.plot(self.x_data, self.y_data)
        plt.ylabel('lift generated (lbs)')
        plt.xlabel('alpha (15 * )')
        
        plt.subplot(212)
        plt.plot(self.x_sub_data, self.y_sub_data)
        plt.xlabel('wing area (ft^2)')
        plt.ylabel('alpha (deg)')
        plt.show()
    

#24in x 6in
#0.6096 m x 0.1524 m
# 0.09290304 m^2
# mph * 1.467 = fps
# kg/m^3 / 515 = slug/ft^3
# 15 mph = 22 fps

# velocity, density, wing_area, alpha
if __name__ == "__main__":

    print("------ Run 1 ------")
    liftObj = Lift(7.77778, 1.225, 15.3, 0)
    liftObj.generate_dynamic_pressure()
    liftObj.toString()

    
    
    print("\n------ Run 2 ------")

    liftObj.set_alpha(10)
    liftObj.generate_dynamic_pressure()
    print(liftObj.find_cl_from_alpha())
    liftObj.toString()

    print("\n------ Plot ------")

    pl = Plotter()
    pl.getPlotData(liftObj, 75)
    pl.plot_data()


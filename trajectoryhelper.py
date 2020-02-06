import math
def main():
    mode = input("Mode? [r]adians to degrees or [t]angents to degrees?\n")
    if mode == "t":
        while True:
            tan_x, tan_y = [float(i) for i in input("Tan x, tan y:\n").split()]
            print(math.degrees(math.atan2(tan_y,tan_x)))
    else:
        while True:
            r = float(input("Radians:\n"))
            print(math.degrees(r))
        
        

if __name__ == "__main__":
    main()

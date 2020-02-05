import math
def main():
    while True:
        tan_x, tan_y = [float(i) for i in input("Tan x, tan y:\n").split()]
        print(math.degrees(math.atan2(tan_y,tan_x)))
        
        

if __name__ == "__main__":
    main()


class Packet():
    def __init__(self, bitdata):
        print(bitdata)
        startframe = 0b001
        

class SendPacket():
    def __init__(self, bitdata):
        print("Send;")

class RecivePacket():
    def __init__(self, bitdata):
        print("Recive;")


if __name__ == "__main__":
    packet = Packet(0b0100)
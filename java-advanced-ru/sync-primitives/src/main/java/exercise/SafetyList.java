package exercise;

class SafetyList {
    // BEGIN
    private int[] elementData = new int[1];

    private int size = 0;

    public synchronized void add(int number) {

        var capacity = size;
        if (capacity + 1 > elementData.length) {
            var newCapacity = (capacity * 3) / 2 + 1;
            var newElementData = new int[newCapacity];
            //создать новый мосив и копировать
            System.arraycopy(elementData, 0, newElementData, 0, size);
            newElementData[size++] = number;
            elementData = newElementData;
        } else {
            elementData[size++] = number;
        }
    }

    public int getSize() {
        return size;
    }
    // END
}

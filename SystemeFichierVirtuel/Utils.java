class Utils {
    public static void writeInt(byte[] memory, int offset, int value) {
        memory[offset] = (byte) (value >> 24);
        memory[offset + 1] = (byte) (value >> 16);
        memory[offset + 2] = (byte) (value >> 8);
        memory[offset + 3] = (byte) value;
    }

    public static int readInt(byte[] memory, int offset) {
        return ((memory[offset] & 0xFF) << 24) |
               ((memory[offset + 1] & 0xFF) << 16) |
               ((memory[offset + 2] & 0xFF) << 8) |
               (memory[offset + 3] & 0xFF);
    }

    public static void writeLong(byte[] memory, int offset, long value) {
        for (int i = 0; i < 8; i++)
            memory[offset + i] = (byte) (value >> (56 - i * 8));
    }

    public static long readLong(byte[] memory, int offset) {
        long value = 0;

        for (int i = 0; i < 8; i++)
            value |= ((long) (memory[offset + i] & 0xFF)) << (56 - i * 8);

        return value;
    }

    public static void writeShort(byte[] memory, int offset, short value) {
        memory[offset] = (byte) (value >> 8);
        memory[offset + 1] = (byte) value;
    }

    public static String readString(byte[] memory, int offset, int maxLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            byte b = memory[offset + i];

            if (b == 0)
                break; // Fin de chaîne

            sb.append((char) b);
        }
        return sb.toString();
    }

    public static void writeString(byte[] memory, int offset, String str, int maxLength) {
        byte[] strBytes = str.getBytes();
        int len = Math.min(strBytes.length, maxLength - 1); // Garder place pour \0

        System.arraycopy(strBytes, 0, memory, offset, len);
        // Compléter avec des zéros
        for (int i = len; i < maxLength; i++)
            memory[offset + i] = 0;
    }
}

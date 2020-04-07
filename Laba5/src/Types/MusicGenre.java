package Types;

/**
 * Набор музыкальных жанров, которые могут предпочитать музыкальные банды.
 */
public enum MusicGenre {

    /**
     * Константа хип-хоп.
     */
    HIP_HOP,

    /**
     * Константа джаз.
     */
    JAZZ,

    /**
     * Константа соул.
     */
    SOUL;

    /**
     * Статический метод для получения поля жанр, который предпочитает музыкальная группа, из строки.
     * @param st считываемая строка.
     * @return музыкальный жанр из набора музыкальных жанров или null.
     */
    public static MusicGenre getMusicGenre(String st) {
        switch (st) {
            case "HIP_HOP":
                return MusicGenre.HIP_HOP;
            case "JAZZ":
                return MusicGenre.JAZZ;
            case "SOUL":
                return MusicGenre.SOUL;
        }
        return null;
    }

}
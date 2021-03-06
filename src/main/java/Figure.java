public class Figure {
    final static String [] BOARD = {
            "                  _        _        _        __       __       _             ",
            "        /\\       |_)      /        | \\      |_       |_       /        |_|   ",
            "       /--\\      |_)      \\_       |_/      |__      |        \\_?      | |   ",
            "                                                                             ",
            "  _           #########         #########         #########         #########",
            " (_)          #########         #########         #########         #########",
            " (_)          #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "  __ #########         #########         #########         #########         ",
            "   / #########         #########         #########         #########         ",
            "  /  #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
            "              #########         #########         #########         #########",
            "  /           #########         #########         #########         #########",
            " (_)          #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "  _  #########         #########         #########         #########         ",
            " |_  #########         #########         #########         #########         ",
            "  _) #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
            "   .          #########         #########         #########         #########",
            "  /|          #########         #########         #########         #########",
            " '-|          #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "  _  #########         #########         #########         #########         ",
            "  _) #########         #########         #########         #########         ",
            "  _) #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
            "  _           #########         #########         #########         #########",
            "   )          #########         #########         #########         #########",
            "  /_          #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "              #########         #########         #########         #########",
            "     #########         #########         #########         #########         ",
            "  /| #########         #########         #########         #########         ",
            "   | #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
            "     #########         #########         #########         #########         ",
    };

    final static String[] WHITE_KING = {
            "         ",
            "  |`+'|  ",
            "  (o$o)  ",
            "   (_)   ",
            "         ",
    };

    final static String[] WHITE_QUEEN = {
        "         ",
        "  /\\v/\\  ",
        " /(o$o)\\ ",
        "   (_)   ",
        "         ",
    };

    final static String[] WHITE_BISHOP = {
        "         ",
        "  '\\v/`  ",
        "  (o$0)  ",
        "   (_)   ",
        "         ",
    };

    final static String[] WHITE_KNIGHT = {
        "         ",
        "  \\`~'/  ",
        "  (o$o)  ",
        "   \\$/$\\ ",
        "    \"    ",
    };

    final static String[] WHITE_ROOK = {
        "         ",
        "  [`'`'] ",
        "   |$$|  ",
        "   |$$|  ",
        "         ",
    };

    final static String[] WHITE_PAWN = {
        "    _    ",
        "   (_)   ",
        "   |$|   ",
        "   |_|   ",
        "         ",
    };

    final static String[] BLACK_KING = {
        "         ",
        "  |:+:|  ",
        "  (o:o)  ",
        "   (:)   ",
        "         ",
    };

    final static String[] BLACK_QUEEN = {
            "         ",
            "  /\\:/\\  ",
            " /(o:o)\\ ",
            "   (:)   ",
            "         ",
    };

    final static String[] BLACK_BISHOP = {
            "         ",
            "  ':v:`  ",
            "  (o:0)  ",
            "   (:)   ",
            "         ",
    };

    final static String[] BLACK_KNIGHT = {
            "         ",
            "  \\`.'/  ",
            "  (o:o)  ",
            "   \\:/:\\ ",
            "    \"    ",
    };

    final static String[] BLACK_ROOK = {
            "         ",
            "  [`'`'] ",
            "   |::|  ",
            "   |::|  ",
            "         ",
    };

    final static String[] BLACK_PAWN = {
            "         ",
            "   (:)   ",
            "   |:|   ",
            "   |:|   ",
            "         ",
    };

    final static String[] EMPTY = {
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
    };
}
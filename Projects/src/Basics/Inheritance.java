package Basics;

public class Inheritance {
    // == FINAL STATIC ============================
    final static String DEFAULT_FINAL_STATIC = "  default final static: ";
    final static String PROTECTED_FINAL_STATIC = "  protected final static: ";
    final static String PUBLIC_FINAL_STATIC = "  public final static: ";
    // == FINAL ====================================
    final static String DEFAULT_FINAL = "  default final: ";
    final static String PROTECTED_FINAL = "  protected final: ";
    final static String PUBLIC_FINAL = "  public final: ";
    // == STATIC ===================================
    final static String DEFAULT_STATIC = "   default static: ";
    final static String PROTECTED_STATIC = "  protected static: ";
    final static String PUBLIC_STATIC = "  public static: ";
    // == COMMON ===================================
    final static String DEFAULT = "  default: ";
    final static String PROTECTED = "  protected: ";
    final static String PUBLIC = "  public: ";

    public static String getNamedID(int id) {
        String name = "";
        Class c = Object.class;
        switch (id) {
            case A.statID:
                c = A.class;
                break;
            case B.statID:
                c = B.class;
                break;
            case C.statID:
                c = C.class;
                break;
        }
        return String.format("%d (%s)", id, c.getName());
    }

    public static void showAll(A var) {
        System.out.println(">>>>>>>>>>>> public static void showAll(A var)");
        System.out.println("~~ FIELDS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("== FINAL STATIC =============================");
        System.out.println(DEFAULT_FINAL_STATIC + getNamedID(var._defFinStat));
        System.out.println(PROTECTED_FINAL_STATIC + getNamedID(var._protFinStat));
        System.out.println(PUBLIC_FINAL_STATIC + getNamedID(var._pubFinStat));
        System.out.println("== FINAL ====================================");
        System.out.println(DEFAULT_FINAL + getNamedID(var._defFin));
        System.out.println(PROTECTED_FINAL + getNamedID(var._protFin));
        System.out.println(PUBLIC_FINAL + getNamedID(var._pubFin));
        System.out.println("== STATIC ===================================");
        System.out.println(DEFAULT_STATIC + getNamedID(var._defStat));
        System.out.println(PROTECTED_STATIC + getNamedID(var._defStat));
        System.out.println(PUBLIC_STATIC + getNamedID(var._defStat));
        System.out.println("== COMMON ===================================");
        System.out.println(DEFAULT + getNamedID(var._def));
        System.out.println(PROTECTED + getNamedID(var._prot));
        System.out.println(PUBLIC + getNamedID(var._pub));
        System.out.println("~~ METHODS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("== FINAL STATIC =============================");
        System.out.println(DEFAULT_FINAL_STATIC + getNamedID(var.defFinStat()));
        System.out.println(PROTECTED_FINAL_STATIC + getNamedID(var.protFinStat()));
        System.out.println(PUBLIC_FINAL_STATIC + getNamedID(var.pubFinStat()));
        System.out.println("== FINAL ====================================");
        System.out.println(DEFAULT_FINAL + getNamedID(var.defFin()));
        System.out.println(PROTECTED_FINAL + getNamedID(var.protFin()));
        System.out.println(PUBLIC_FINAL + getNamedID(var.pubFin()));
        System.out.println("== STATIC ===================================");
        System.out.println(DEFAULT_STATIC + getNamedID(var.defStat()));
        System.out.println(PROTECTED_STATIC + getNamedID(var.defStat()));
        System.out.println(PUBLIC_STATIC + getNamedID(var.defStat()));
        System.out.println("== COMMON ===================================");
        System.out.println(DEFAULT + getNamedID(var.def()));
        System.out.println(PROTECTED + getNamedID(var.prot()));
        System.out.println(PUBLIC + getNamedID(var.pub()));
        System.out.println();
    }

    public static void showAll(B var) {
        System.out.println(">>>>>>>>>>>> public static void showAll(B var)");
        System.out.println("~~ FIELDS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("== FINAL STATIC =============================");
        System.out.println(DEFAULT_FINAL_STATIC + getNamedID(var._defFinStat));
        System.out.println(PROTECTED_FINAL_STATIC + getNamedID(var._protFinStat));
        System.out.println(PUBLIC_FINAL_STATIC + getNamedID(var._pubFinStat));
        System.out.println("== FINAL ====================================");
        System.out.println(DEFAULT_FINAL + getNamedID(var._defFin));
        System.out.println(PROTECTED_FINAL + getNamedID(var._protFin));
        System.out.println(PUBLIC_FINAL + getNamedID(var._pubFin));
        System.out.println("== STATIC ===================================");
        System.out.println(DEFAULT_STATIC + getNamedID(var._defStat));
        System.out.println(PROTECTED_STATIC + getNamedID(var._defStat));
        System.out.println(PUBLIC_STATIC + getNamedID(var._defStat));
        System.out.println("== COMMON ===================================");
        System.out.println(DEFAULT + getNamedID(var._def));
        System.out.println(PROTECTED + getNamedID(var._prot));
        System.out.println(PUBLIC + getNamedID(var._pub));
        System.out.println("~~ METHODS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("== FINAL STATIC =============================");
        System.out.println(DEFAULT_FINAL_STATIC + getNamedID(var.defFinStat()));
        System.out.println(PROTECTED_FINAL_STATIC + getNamedID(var.protFinStat()));
        System.out.println(PUBLIC_FINAL_STATIC + getNamedID(var.pubFinStat()));
        System.out.println("== FINAL ====================================");
        System.out.println(DEFAULT_FINAL + getNamedID(var.defFin()));
        System.out.println(PROTECTED_FINAL + getNamedID(var.protFin()));
        System.out.println(PUBLIC_FINAL + getNamedID(var.pubFin()));
        System.out.println("== STATIC ===================================");
        System.out.println(DEFAULT_STATIC + getNamedID(var.defStat()));
        System.out.println(PROTECTED_STATIC + getNamedID(var.defStat()));
        System.out.println(PUBLIC_STATIC + getNamedID(var.defStat()));
        System.out.println("== COMMON ===================================");
        System.out.println(DEFAULT + getNamedID(var.def()));
        System.out.println(PROTECTED + getNamedID(var.prot()));
        System.out.println(PUBLIC + getNamedID(var.pub()));
        System.out.println();
    }

//    public static void showAll(C var) {
//        System.out.println(">>>>>>>>>>>> public static void showAll(C var)");
//        System.out.println("~~ FIELDS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("== FINAL STATIC =============================");
//        System.out.println(DEFAULT_FINAL_STATIC + getNamedID(var._defFinStat));
//        System.out.println(PROTECTED_FINAL_STATIC + getNamedID(var._protFinStat));
//        System.out.println(PUBLIC_FINAL_STATIC + getNamedID(var._pubFinStat));
//        System.out.println("== FINAL ====================================");
//        System.out.println(DEFAULT_FINAL + getNamedID(var._defFin));
//        System.out.println(PROTECTED_FINAL + getNamedID(var._protFin));
//        System.out.println(PUBLIC_FINAL + getNamedID(var._pubFin));
//        System.out.println("== STATIC ===================================");
//        System.out.println(DEFAULT_STATIC + getNamedID(var._defStat));
//        System.out.println(PROTECTED_STATIC + getNamedID(var._defStat));
//        System.out.println(PUBLIC_STATIC + getNamedID(var._defStat));
//        System.out.println("== COMMON ===================================");
//        System.out.println(DEFAULT + getNamedID(var._def));
//        System.out.println(PROTECTED + getNamedID(var._prot));
//        System.out.println(PUBLIC + getNamedID(var._pub));
//        System.out.println("~~ METHODS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("== FINAL STATIC =============================");
//        System.out.println(DEFAULT_FINAL_STATIC + getNamedID(var.defFinStat()));
//        System.out.println(PROTECTED_FINAL_STATIC + getNamedID(var.protFinStat()));
//        System.out.println(PUBLIC_FINAL_STATIC + getNamedID(var.pubFinStat()));
//        System.out.println("== FINAL ====================================");
//        System.out.println(DEFAULT_FINAL + getNamedID(var.defFin()));
//        System.out.println(PROTECTED_FINAL + getNamedID(var.protFin()));
//        System.out.println(PUBLIC_FINAL + getNamedID(var.pubFin()));
//        System.out.println("== STATIC ===================================");
//        System.out.println(DEFAULT_STATIC + getNamedID(var.defStat()));
//        System.out.println(PROTECTED_STATIC + getNamedID(var.defStat()));
//        System.out.println(PUBLIC_STATIC + getNamedID(var.defStat()));
//        System.out.println("== COMMON ===================================");
//        System.out.println(DEFAULT + getNamedID(var.def()));
//        System.out.println(PROTECTED + getNamedID(var.prot()));
//        System.out.println(PUBLIC + getNamedID(var.pub()));
//        System.out.println();
//    }

    public static void main(String[] args) {
        B b = new B();
        System.out.println("B b = new B();");
        showAll(b);

        A bToA = new B();
        System.out.println("A bToA = new B();");
        showAll(bToA);

        B cToB = new C();
        System.out.println("B cToB = new C();");
        showAll(cToB);

        A cToA = new C();
        System.out.println("A cToA = new C();");
        showAll(cToA);

        DC dc = new DC();
        System.out.println("DC dc = new DC();");
        showAll(dc);

        EB eb = new EB();
        System.out.println("EB eb = new EB();");
        showAll(eb);

        FA fa = new FA();
        System.out.println("FA fa = new FA();");
        showAll(fa);
    }
}

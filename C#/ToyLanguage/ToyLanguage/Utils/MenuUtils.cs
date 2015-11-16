using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Utils
{
    class MenuUtils
    {
        public static String getChooseStatement()
        {
            return "-- " + "1 Compound statement" + "\n-- " + "2 Assign statement" + "\n-- " + "3 If statement" + "\n-- " + "4 Print statement";
        }


        public static String getSteps()
        {
            return "-- 1 for One step\n-- 2 for All steps";
        }

        public static String EXPRESSIONS = "-- 1 Arithmetic\n--2 Constant\n--3 Variable";
    }
}

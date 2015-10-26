using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Interfaces
{
    interface IExpressionscs
    {
        int eval(IMyDictionary<string, int> table);

        string toString();
    }
}

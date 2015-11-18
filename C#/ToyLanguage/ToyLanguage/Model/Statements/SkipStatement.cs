﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    [Serializable]
    class SkipStatement : IMyStatement
    {
        public string MyToString()
        {
            return "Skip";
        }

    }
}

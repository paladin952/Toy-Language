using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Collections
{
    class MyHeap<K, V> : WrapperDictionary<int, int>, IHeap<int, int>
    {
    }
}

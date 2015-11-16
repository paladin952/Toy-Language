using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Collections
{
    
    class WrapperDictionary<K, V> : IMyDictionary<K, V>
    {
        private Dictionary<K, V> map;
            
            public WrapperDictionary()
        {
            map = new Dictionary<K, V>();
        } 

        public V lookUp(K id)
        {
            return map[id];
        }

        public bool isEmpty()
        {
            if(map.Count == 0)
            {
                return true;
            }
            return false;
        }

        public int size()
        {
            return map.Count;
        }

        public bool containsKey(K key)
        {
            return map.ContainsKey(key);
        }

        public void put(K key, V value)
        {
            map.Add(key, value);
        }

        public void remove(K key)
        {
            map.Remove(key);
        }

        public string ToString()
        {
            StringBuilder result = new StringBuilder();

            foreach (KeyValuePair<K, V> entry in map)
            {
                result.Append(entry.Key + "->" + entry.Value + "\n");
            }
            return result.ToString();
        }

    }
}

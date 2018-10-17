package com.hupp.kotlindemo.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.hupp.kotlindemo.R
import com.hupp.kotlindemo.model.FoodModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.food_rowview.view.*

class HomeFragment : Fragment() {
    var adapter: foodAdapter? = null
    var foodsList = ArrayList<FoodModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_home, container, false)

        return rootView
    }

    class foodAdapter : BaseAdapter {
        var foodsList = ArrayList<FoodModel>()
        var context: Context? = null

        constructor(context: Context, foodsList: ArrayList<FoodModel>) : super() {
            this.context = context
            this.foodsList = foodsList
        }

        override fun getCount(): Int {
            return foodsList.size
        }

        override fun getItem(position: Int): Any {
            return foodsList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val food = this.foodsList[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflator.inflate(R.layout.food_rowview, null)
            foodView.imgFood.setImageResource(food.image!!)
            foodView.tvName.text = food.name!!
            foodView.tvPrice.text = food.amount?.toString()!!
            foodView.ratingBar.rating = food.rating!!
            /*foodView.setOnClickListener(View.OnClickListener { val intent = Intent(context, ShoesDetailScreenActivity::class.java)
                intent.putExtra("name", shoe.name!!)
                //intent.putExtra("description", shoe.description!!)
                intent.putExtra("image", shoe.image!!)
                context!!.startActivity(intent) })*/

            return foodView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //var editTextHome = view.findViewById<EditText>(R.id.editTextHome)
        // load foods
        foodsList.add(FoodModel("Coffee", "Coffee preparation is the process of turning coffee beans into a beverage. While the particular steps vary with the type of coffee and with the raw materials, the process includes four basic steps; raw coffee beans must be roasted, the roasted coffee beans must then be ground, the ground coffee must then be mixed with hot water for a certain time (brewed), and finally the liquid coffee must be separated from the used grounds.",
                R.drawable.coffee, 4.5f, 75f))
        foodsList.add(FoodModel("Espresso", "Espresso’s authentic formula is clear and basic, its proper execution a matter of training, experience and natural talent.  A jet of hot water at 88°-93°C (190°-200°F) passes under a pressure of nine or more atmospheres through a seven-gram (.25 oz) cake-like layer of ground and tamped coffee. Done right, the result is a concentrate of not more than 30 ml (one oz) of pure sensorial pleasure.",
                R.drawable.espresso3, 4f, 85f))
        foodsList.add(FoodModel("French Fries", "Heat a few inches of vegetable oil to 300 degrees F in a heavy pot. In 3 or 4 batches, fry the potatoes about 4 to 5 minutes per batch, or until soft. They should not be brown at all at this point-you just want to start the cooking process. Remove each batch and drain them on new, dry paper towels.",
                R.drawable.frenchfries, 4.5f, 65f))
        foodsList.add(FoodModel("Honey", "While it is less likely that anyone would do this on their own if they are not a beekeeper, this might be useful for those who aspire to become one. Bees are really great and easy to keep, even in the urban environment! As Novella Carpenter calls them, bees are &quot;gateway animal for urban farmers&quot;. All you need is some space in the backyard/deck. The process of honey harvesting and extraction most likely happens on a separate days.",
                R.drawable.honey, 3.5f, 95f))
        foodsList.add(FoodModel("Strawberry", "Preparation. Coarsely mash strawberries with sugar, lemon juice, and salt using a potato masher in a large bowl. Let stand, stirring and mashing occasionally, 10 minutes. Transfer half of strawberry mixture to a blender and purée with cream until smooth. Freeze mixture in ice cream maker.",
                R.drawable.strawberry1, 4f, 80f))
        foodsList.add(FoodModel("Sugar cubes", "Sugar cubes are extremely simple to make at home - all you need is sugar and water. In addition to standard cubes, you can add color and flavor to add fun flair to a tea party or another gathering. Learn how to make sugar cubes using two different methods: using a pan in the oven or an ice cube tray you leave out overnight.",
                R.drawable.sugar1, 3f, 70f))
        foodsList.add(FoodModel("Tea", "Tea is an aromatic beverage commonly prepared by pouring hot or boiling water over cured leaves of the Camellia sinensis, an evergreen shrub (bush) native to Asia. After water, it is the most widely consumed drink in the world. There are many different types of tea; some, like Darjeeling and Chinese greens, have a cooling, slightly bitter, and astringent flavour, while others have vastly different profiles that include sweet, nutty, floral or grassy notes.",
                R.drawable.tea2, 5f, 60f))
        adapter = foodAdapter(this.context!!, foodsList)

        //var gvFoods = view.findViewById<GridView>(R.id.gvFoods)
        gvFoods.adapter = adapter
    }

    companion object {
        fun newInstance(): HomeFragment {
            var homeFragment = HomeFragment()
            var args = Bundle()
            homeFragment.arguments = args
            return homeFragment
        }
    }
}// Required empty public constructor


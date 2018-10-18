package com.hupp.kotlindemo.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hupp.kotlindemo.FoodDetailScreenActivity

import com.hupp.kotlindemo.R
import com.hupp.kotlindemo.model.FoodModel
import kotlinx.android.synthetic.main.food_rowview.view.*

class LikeFragment : Fragment() {
    var listView: ListView? = null
    var foodsList = ArrayList<FoodModel>()
    var adapter: FoodListViewAdapter? = null
    val TAG = "LikeFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_like, container, false).apply { tag = TAG }

        listView = view.findViewById<ListView>(R.id.listView)

        listView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // This is your listview's selected item
            val item = parent.getItemAtPosition(position)
            val intent = Intent(context, FoodDetailScreenActivity::class.java)
            intent.putExtra("name", foodsList[position].name!!)
            intent.putExtra("description", foodsList[position].description!!)
            intent.putExtra("amount", foodsList[position].amount!!)
            intent.putIntegerArrayListExtra("image", ArrayList(foodsList[position].imageList!!))
            context!!.startActivity(intent)
        }

        prepareLikeFoodData()

        adapter = FoodListViewAdapter(Activity(), foodsList)

        return view
    }

    private fun prepareLikeFoodData() {
        foodsList.add(FoodModel("Coffee", "Coffee preparation is the process of turning coffee beans into a beverage. While the particular steps vary with the type of coffee and with the raw materials, the process includes four basic steps; raw coffee beans must be roasted, the roasted coffee beans must then be ground, the ground coffee must then be mixed with hot water for a certain time (brewed), and finally the liquid coffee must be separated from the used grounds.",
                R.drawable.coffee, 4.5f, 75f, listOf(R.drawable.coffee, R.drawable.coffee1, R.drawable.coffee2, R.drawable.coffe3)))
        foodsList.add(FoodModel("Espresso", "Espresso’s authentic formula is clear and basic, its proper execution a matter of training, experience and natural talent.  A jet of hot water at 88°-93°C (190°-200°F) passes under a pressure of nine or more atmospheres through a seven-gram (.25 oz) cake-like layer of ground and tamped coffee. Done right, the result is a concentrate of not more than 30 ml (one oz) of pure sensorial pleasure.",
                R.drawable.espresso3, 4f, 85f, listOf(R.drawable.espresso3, R.drawable.espresso2, R.drawable.espresso1, R.drawable.espresso)))
        foodsList.add(FoodModel("French Fries", "Heat a few inches of vegetable oil to 300 degrees F in a heavy pot. In 3 or 4 batches, fry the potatoes about 4 to 5 minutes per batch, or until soft. They should not be brown at all at this point-you just want to start the cooking process. Remove each batch and drain them on new, dry paper towels.",
                R.drawable.frenchfries, 4.5f, 65f, listOf(R.drawable.frenchfries, R.drawable.frenchfries1, R.drawable.frenchfries2, R.drawable.frenchfries3)))
        foodsList.add(FoodModel("Honey", "While it is less likely that anyone would do this on their own if they are not a beekeeper, this might be useful for those who aspire to become one. Bees are really great and easy to keep, even in the urban environment! As Novella Carpenter calls them, bees are &quot;gateway animal for urban farmers&quot;. All you need is some space in the backyard/deck. The process of honey harvesting and extraction most likely happens on a separate days.",
                R.drawable.honey, 3.5f, 95f, listOf(R.drawable.honey, R.drawable.honey1, R.drawable.honey2, R.drawable.honey3)))
        foodsList.add(FoodModel("Strawberry", "Preparation. Coarsely mash strawberries with sugar, lemon juice, and salt using a potato masher in a large bowl. Let stand, stirring and mashing occasionally, 10 minutes. Transfer half of strawberry mixture to a blender and purée with cream until smooth. Freeze mixture in ice cream maker.",
                R.drawable.strawberry1, 4f, 80f, listOf(R.drawable.strawberry1, R.drawable.strawberry, R.drawable.strawberry2, R.drawable.strawberry3)))
        foodsList.add(FoodModel("Sugar cubes", "Sugar cubes are extremely simple to make at home - all you need is sugar and water. In addition to standard cubes, you can add color and flavor to add fun flair to a tea party or another gathering. Learn how to make sugar cubes using two different methods: using a pan in the oven or an ice cube tray you leave out overnight.",
                R.drawable.sugar1, 3f, 70f, listOf(R.drawable.sugar1, R.drawable.sugar, R.drawable.sugar2, R.drawable.sugar3)))
        foodsList.add(FoodModel("Tea", "Tea is an aromatic beverage commonly prepared by pouring hot or boiling water over cured leaves of the Camellia sinensis, an evergreen shrub (bush) native to Asia. After water, it is the most widely consumed drink in the world. There are many different types of tea; some, like Darjeeling and Chinese greens, have a cooling, slightly bitter, and astringent flavour, while others have vastly different profiles that include sweet, nutty, floral or grassy notes.",
                R.drawable.tea2, 5f, 60f, listOf(R.drawable.tea2, R.drawable.tea, R.drawable.tea1, R.drawable.tea3)))
        adapter = FoodListViewAdapter(this.context!!, foodsList)

        listView!!.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    inner class FoodListViewAdapter : BaseAdapter {

        private var foodList = ArrayList<FoodModel>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<FoodModel>) : super() {
            this.foodList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.like_rowview, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            val food = foodList[position]

            vh.tvName.text = food.name!!
            vh.tvPrice.text = "$".plus(" ").plus(food.amount!!)
            vh.ratingBar.rating = food.rating!!
            vh.imgFood.setImageResource(food.image!!)
            /*view!!.setOnClickListener(View.OnClickListener {
                Toast.makeText(getContext(), food.name!!, Toast.LENGTH_SHORT).show()
            })*/

            return view
        }

        override fun getItem(position: Int): Any {
            return foodList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return foodList.size
        }
    }

    private class ViewHolder(view: View?) {
        val tvName: TextView
        val tvPrice: TextView
        val ratingBar: RatingBar
        val imgFood: ImageView

        init {
            this.tvName = view!!.findViewById<TextView>(R.id.tvName)
            this.tvPrice = view.findViewById<TextView>(R.id.tvPrice)
            this.ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
            this.imgFood=view.findViewById(R.id.imgFood)
        }
    }
}// Required empty public constructor

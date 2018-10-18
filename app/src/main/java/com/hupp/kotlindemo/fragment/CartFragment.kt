package com.hupp.kotlindemo.fragment

import android.graphics.*
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

import com.hupp.kotlindemo.R
import com.hupp.kotlindemo.model.FoodModel

class CartFragment : Fragment() {
    private var adapter: DataAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var tvEmpty: TextView? = null
    private var edit_position: Int = 0
    private var add = false
    private val p = Paint()
    var foodsList = ArrayList<FoodModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.card_recycler_view)
        tvEmpty = view.findViewById(R.id.tvEmpty)
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager

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
        adapter = DataAdapter(foodsList)

        recyclerView!!.adapter = adapter
        adapter?.notifyDataSetChanged()

        if (foodsList.size > 0) {
            recyclerView!!.visibility = View.VISIBLE
            tvEmpty!!.visibility = View.GONE
        } else {
            recyclerView!!.visibility = View.GONE
            tvEmpty!!.visibility = View.VISIBLE
        }


        initSwipe()

    }

    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                adapter!!.removeItem(position)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                val icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    if (dX > 0) {
                        p.color = Color.parseColor("#388E3C")
                        val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_delete_white1)
                        val icon_dest = RectF(itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, icon_dest, p)
                    } else {
                        p.color = Color.parseColor("#D32F2F")
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_delete_white1)
                        val icon_dest = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, icon_dest, p)
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun removeView() {
        if (view!!.parent != null) {
            (view!!.parent as ViewGroup).removeView(view)
        }
    }

    /*private class DataAdapter(private val names: ArrayList<FoodModel>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DataAdapter.ViewHolder {
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_layout, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

            viewHolder.tv_names.text = names[i]
        }

        override fun getItemCount(): Int {
            return names.size
        }

        fun addItem(country: String) {
            names.add(country)
            notifyItemInserted(names.size)
        }

        fun removeItem(position: Int) {
            names.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, names.size)
        }

        internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var tv_names: TextView

            init {

                tv_names = view.findViewById(R.id.tv_names) as TextView
            }
        }
    }*/

    class DataAdapter(private var foodsList: List<FoodModel>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var tvName: TextView = view.findViewById<TextView>(R.id.tvName)
            var tvPrice: TextView = view.findViewById<TextView>(R.id.tvPrice)
            var ratingBar: RatingBar = view.findViewById<RatingBar>(R.id.ratingBar)
            var imgFood: ImageView = view.findViewById(R.id.imgFood)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.like_rowview, parent, false)

            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val food = foodsList[position]
            holder.tvName.text = food.name!!
            holder.tvPrice.text = "$".plus(" ").plus(food.amount!!)
            holder.ratingBar.rating = food.rating!!
            holder.imgFood.setImageResource(food.image!!)
        }

        override fun getItemCount(): Int {
            return foodsList.size
        }

        fun removeItem(position: Int) {
            val mutableList = foodsList.toMutableList()
            mutableList.removeAt(position)
            notifyItemRemoved(position)
            foodsList = mutableList
            notifyItemRangeChanged(position, foodsList.size)
        }
    }
}// Required empty public constructor


import com.example.smarttrade.catalogue.viewmodel.Product
import retrofit2.Response

interface ProductRepository {
    suspend fun createProduct(
        product: Product,
        price: Double,
        stock: Int,
        sellerEmail: String,
    ): Response<String>
}
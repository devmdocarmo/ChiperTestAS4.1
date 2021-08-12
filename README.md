# ChiperTestAS4.1
Aplicacion para demostrar mis habilidades en conexiones a servicio, arquitectura aplicada MVVM y herramientas agiles del lenguaje Kotlin

Application to demonstrate my skills in service connections, MVVM applied architecture and agile tools of the Kotlin language

## View Model
  Instancias necesarias para la llamada a la API de peliculas y asi poder hacer una carga limpia de los datos obtenidos del servicio y variable de paginado para el correspondiente parametro
  
  Instances necessary for the call to the API of movies and thus be able to make a clean load of the data obtained from the service and paging variable for the corresponding parameter

  ```Kotlin
    class MainViewModel : ViewModel() {
        //instance Retrofit
        private val retrofit = RetrofitService().instance.create<TMDBAPI>()
        private var page: Int= 1
        //instance LiveData
        private val listMovies: MutableLiveData<ListMovies> by lazy {
            MutableLiveData<ListMovies>()
        }
        ...
  ```

### Functions
  Funciones necesarias para la implementacion del Modelo para el ViewModel y realizacion correcta de llamadas Asincronas con la libreria Retrofit2

  Necessary functions for the implementation of the Model for the ViewModel and correct execution of Asynchronous calls with the Retrofit2 library

  ```Kotlin
    ...
    fun getMovies(): LiveData<ListMovies> {
        //get LiveData values
        return listMovies
    }

    fun loadAListofMovies(pageNumber: Int) {
        // Do an asynchronous operation to fetch movies.
        retrofit.getListofMovies(10, page = pageNumber, apiKey = BuildConfig.ACCESS_TOKEN)
            .enqueue(object : Callback<ListMovies> {
                override fun onResponse(call: Call<ListMovies>, response: Response<ListMovies>) {
                    if(response.isSuccessful) {
                        Log.d("RESPONSE", response.body().toString())
                        listMovies.value?.results?.clear()
                        listMovies.value = response.body()
                    }else{
                        Log.e("RESPONSE", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<ListMovies>, t: Throwable) {
                    Log.e("RESPONSE", t.message.toString())
                }

            }
        )
    }
}
  ```

### Inits
  En el fragment utilizado se inicializa el proveedor con el modelo de ViewModel creado

  In the fragment used, the provider is initialized with the ViewModel created

```Kotlin
//setting the viewModelProvider
viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
```

### Uses
En el fragment utilizado se esta a la espera del nuevo dato (```listMovies```), que vendra de la llamada al servicio

In the fragment used, we are waiting for the new data (```listMovies```), which will come from the call to the service

```Kotlin
private fun initViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner){
            Log.d("RECYCLER","LISTA ANTES DE CAMBIO\n" +
                    "tam: ${listofMovies.size}, tam comming: ${it.results.size}\n"+
                    "page: $pageToView")
            val volatil =ArrayList<Result>()
            val lastPositionOld = listofMovies.size
            volatil.addAll(it.results)
            isOver = it.results.isEmpty()
            listofMovies.addAll(volatil)
            pageToView++
            Log.d("RECYCLER","LISTA DESPUES DE CAMBIO\n" +
                    "tam: ${listofMovies.size}")
            moviesAdapter.notifyItemInserted(lastPositionOld)
            isLoading = false
        }
    }
```
---
## Unit Tests
Un pequeño archivo para hacer tests sobre nullidad del ViewModel y el dato que hay dentro suyo

A small file to do tests on the nullity of the ViewModel and the data that is inside it

```Kotlin
class MainViewModelTest {
    private lateinit var mViewModel: MainViewModel
    @Before
    fun setUp(){
        mViewModel= MainViewModel()
    }

    @Test
    fun modelNotNull(){
        assertNotNull(mViewModel)
    }

    @Test
    fun getMovies() {
        assertNotNull(mViewModel.getMovies())
    }

}
```
---
#### Created By: Miguel Do Carmo
Android developer 2021, thank you for your consideration
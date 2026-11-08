package com.example.calculatoroftrainingcoefficients

import android.os.Bundle
import android.os.Parcel
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.calculatoroftrainingcoefficients.core.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorStateTest {
    private fun onMain(block: () -> Unit) { InstrumentationRegistry.getInstrumentation().runOnMainSync(block) }
    @Test fun deletesLastEntryAndUndoPreservesNewEntries() = onMain {
        val vm = MainViewModel(SavedStateHandle())
        vm.addElement(5); val id = vm.numbersList.single().id
        vm.deleteElement(id); assertNull(vm.result)
        vm.addElement(2); assertTrue(vm.undoDelete())
        assertEquals(listOf(5, 2), vm.numbersList.map { it.number })
        assertEquals(3.5, vm.result!!, 1e-12)
        vm.editElement(id, 4, 2.0); assertEquals(10.0 / 3, vm.result!!, 1e-12)
        vm.deleteElement("missing"); assertEquals(2, vm.numbersList.size)
    }
    @Test fun restoresParcelledStateIntoNewViewModel() = onMain {
        val state = SavedStateHandle(); val vm = MainViewModel(state)
        vm.selectWeight(1.3); vm.addElement(4)
        val bundle = Bundle().apply {
            putStringArrayList(MainViewModel.ENTRIES, state[MainViewModel.ENTRIES])
            putDouble(MainViewModel.WEIGHT, state[MainViewModel.WEIGHT]!!)
        }
        val parcel = Parcel.obtain()
        val restoredBundle = try {
            parcel.writeBundle(bundle); parcel.setDataPosition(0); parcel.readBundle(javaClass.classLoader)!!
        } finally { parcel.recycle() }
        val restored = MainViewModel(SavedStateHandle(mapOf(
            MainViewModel.ENTRIES to restoredBundle.getStringArrayList(MainViewModel.ENTRIES),
            MainViewModel.WEIGHT to restoredBundle.getDouble(MainViewModel.WEIGHT)
        )))
        assertEquals(vm.numbersList, restored.numbersList)
        assertEquals(1.3, restored.selectedWeight, 0.0)
        assertEquals(4.0, restored.result!!, 1e-12)
    }
    @Test fun rejectsMalformedRestoredRecords() = onMain {
        val vm = MainViewModel(SavedStateHandle(mapOf(
            MainViewModel.ENTRIES to arrayListOf("bad", "a|5|0", "b|8|1", "c|4|1.5", "c|4|1.5"),
            MainViewModel.WEIGHT to Double.NaN
        )))
        assertEquals(1, vm.numbersList.size); assertEquals(4.0, vm.result!!, 0.0)
        assertEquals(1.0, vm.selectedWeight, 0.0)
    }
    @Test fun enforcesLimitWithoutSilentlyDroppingUndoRecords() = onMain {
        val vm = MainViewModel(SavedStateHandle())
        repeat(MAX_ENTRIES) { assertTrue(vm.addElement(5)) }
        assertFalse(vm.addElement(2))
        vm.clear(); vm.addElement(3)
        assertFalse(vm.undoDelete()); assertEquals(listOf(3), vm.numbersList.map { it.number })
    }
}
